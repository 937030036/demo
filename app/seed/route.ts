import bcrypt from 'bcrypt';
import { invoices, customers, revenue, users } from '../lib/placeholder-data';
import mysql, { PoolConnection } from 'mysql2/promise'
export const pool = mysql.createPool({
  connectionLimit: 10,
  host: process.env.DB_HOST_URL,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE
});

async function seedUsers(conn: Promise<PoolConnection>) {
  (await conn).execute(`
    CREATE TABLE IF NOT EXISTS users (
      id VARCHAR(255) PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL
    );
  `);

  const insertedUsers = await Promise.all(
    users.map(async (user) => {
      const hashedPassword = await bcrypt.hash(user.password, 10);
      return (await conn).execute(`
        INSERT INTO users (id, name, email, password)
        VALUES ("${user.id}", "${user.name}", "${user.email}", "${hashedPassword}");
      `);
    }),
  );

  return insertedUsers;
}

async function seedInvoices(conn: Promise<PoolConnection>) {

  await (await conn).execute(`
    CREATE TABLE IF NOT EXISTS invoices (
      id INT(4) PRIMARY KEY AUTO_INCREMENT,
      customer_id VARCHAR(255) NOT NULL,
      amount INT NOT NULL,
      status VARCHAR(255) NOT NULL,
      date DATE NOT NULL
    );
  `);

  const insertedInvoices = await Promise.all(
    invoices.map(
      async (invoice) => (await conn).execute(`
        INSERT INTO invoices (customer_id, amount, status, date)
        VALUES ("${invoice.customer_id}", ${invoice.amount}, "${invoice.status}", "${invoice.date}");
      `),
    ),
  );

  return insertedInvoices;
}

async function seedCustomers(conn: Promise<PoolConnection>) {

  await (await conn).execute(`
    CREATE TABLE IF NOT EXISTS customers (
      id VARCHAR(255) PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) NOT NULL,
      image_url VARCHAR(255) NOT NULL
    );
  `);

  const insertedCustomers = await Promise.all(
    customers.map(
      async (customer) => (await conn).execute(`
        INSERT INTO customers (id, name, email, image_url)
        VALUES ("${customer.id}", "${customer.name}", "${customer.email}", "${customer.image_url}");
      `),
    ),
  );

  return insertedCustomers;
}

async function seedRevenue(conn: Promise<PoolConnection>) {
  await (await conn).execute(`
    CREATE TABLE IF NOT EXISTS revenue (
      month VARCHAR(4) NOT NULL UNIQUE,
      revenue INT NOT NULL
    );
  `);

  const insertedRevenue = await Promise.all(
    revenue.map(
      async (rev) => (await conn).execute(`
        INSERT INTO revenue (month, revenue)
        VALUES ("${rev.month}", ${rev.revenue});
      `),
    ),
  );

  return insertedRevenue;
}

export async function GET() {
  const conn = pool.getConnection();
  try {
    await seedUsers(conn);
    await seedCustomers(conn);
    await seedInvoices(conn);
    await seedRevenue(conn);
    (await conn).commit();
    (await conn).release();

    return Response.json({ message: 'Database seeded successfully' });
  } catch (error) {
    (await conn).rollback()
    return Response.json({ error }, { status: 500 });
  }
}
