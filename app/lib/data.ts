import { sql } from '@vercel/postgres';
import { pool } from '../seed/route';
const conn = pool.getConnection();
import {
  CustomerField,
  CustomersTableType,
  Invoice,
  InvoiceForm,
  InvoicesTable,
  LatestInvoice,
  LatestInvoiceRaw,
  Revenue,
} from './definitions';
import { formatCurrency } from './utils';
import { RowDataPacket } from 'mysql2/promise';

export async function fetchRevenue() {
  try {
    // Artificially delay a response for demo purposes.
    // Don't do this in production :)

    console.log('Fetching revenue data...');

    // const data = await sql<Revenue>`SELECT * FROM revenue`;
    const lst: Revenue[] = []
    const data = await (await conn).query<RowDataPacket[]>(`SELECT * FROM revenue`);
    for (var item of data[0]) {
      var i: Revenue = {
        month: item.month,
        revenue: item.revenue
      }
      lst.push(i)
    }

    return lst;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch revenue data.');
  }
}

export async function fetchLatestInvoices() {
  try {
    // const data = await sql<LatestInvoiceRaw>`
    //   SELECT invoices.amount, customers.name, customers.image_url, customers.email, invoices.id
    //   FROM invoices
    //   JOIN customers ON invoices.customer_id = customers.id
    //   ORDER BY invoices.date DESC
    //   LIMIT 5`;
    const data = await (await conn).query<RowDataPacket[]>(`
      SELECT invoices.amount, customers.name, customers.image_url, customers.email, invoices.id
      FROM invoices
      JOIN customers ON invoices.customer_id = customers.id
      ORDER BY invoices.date DESC
      LIMIT 5`);
    var lst: LatestInvoice[] = []
    for (var item of data[0]) {
      var i: LatestInvoice = {
        id: item.id,
        name: item.name,
        image_url: item.image_url,
        email: item.email,
        amount: formatCurrency(item.amount),
      };
      lst.push(i);
    }
    // const latestInvoices = data[0].map((invoice) => ({
    //   ...invoice,
    //   amount: formatCurrency(invoice.amount),
    // }));
    return lst;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch the latest invoices.');
  }
}

export async function fetchCardData() {
  try {
    // You can probably combine these into a single SQL query
    // However, we are intentionally splitting them to demonstrate
    // how to initialize multiple queries in parallel with JS.
    const [invoiceCountPromise] = await (await conn).query<RowDataPacket[]>(`SELECT COUNT(*) as count FROM invoices`);
    const [customerCountPromise] = await (await conn).query<RowDataPacket[]>(`SELECT COUNT(*) as count FROM customers`);
    const [invoiceStatusPromise] = await (await conn).query<RowDataPacket[]>(`SELECT
         SUM(CASE WHEN status = 'paid' THEN amount ELSE 0 END) AS "paid",
         SUM(CASE WHEN status = 'pending' THEN amount ELSE 0 END) AS "pending"
         FROM invoices`);

    // const data = await Promise.all([
    //   invoiceCountPromise,
    //   customerCountPromise,
    //   invoiceStatusPromise,
    // ]);
    const numberOfInvoices = Number(invoiceCountPromise[0].count ?? '0');
    const numberOfCustomers = Number(customerCountPromise[0].count ?? '0');
    const totalPaidInvoices = formatCurrency(Number(invoiceStatusPromise[0].paid) ?? '0');
    const totalPendingInvoices = formatCurrency(Number(invoiceStatusPromise[0].pending) ?? '0');

    return {
      numberOfCustomers,
      numberOfInvoices,
      totalPaidInvoices,
      totalPendingInvoices,
    };
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch card data.');
  }
}

const ITEMS_PER_PAGE = 6;
export async function fetchFilteredInvoices(
  query: string,
  currentPage: number,
) {
  const offset = (currentPage - 1) * ITEMS_PER_PAGE;

  try {
    const invoices = await (await conn).query<RowDataPacket[]>(`
      SELECT
        invoices.id,
        invoices.amount,
        invoices.date,
        invoices.status,
        customers.name,
        customers.email,
        customers.image_url
      FROM invoices
      JOIN customers ON invoices.customer_id = customers.id
      WHERE
        customers.name LIKE '${`%${query}%`}' OR
        customers.email LIKE '${`%${query}%`}' OR
        invoices.amount LIKE '${`%${query}%`}' OR
        invoices.date LIKE '${`%${query}%`}' OR
        invoices.status LIKE '${`%${query}%`}'
      ORDER BY invoices.date DESC
      LIMIT ${ITEMS_PER_PAGE} OFFSET ${offset}
    `);
    var res: InvoicesTable[] = []
    for (var item of invoices[0]) {
      var i: InvoicesTable = {
        id: item.id,
        customer_id: item.customer,
        name: item.name,
        email: item.email,
        image_url: item.image_url,
        date: item.date,
        amount: item.amount,
        status: item.status
      }
      res.push(i)
    }

    return res;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch invoices.');
  }
}

export async function fetchInvoicesPages(query: string) {
  try {
    const [count] = await (await conn).query<RowDataPacket[]>(`SELECT COUNT(*) as count
    FROM invoices
    JOIN customers ON invoices.customer_id = customers.id
    WHERE
      customers.name LIKE '${`%${query}%`}' OR
      customers.email LIKE '${`%${query}%`}' OR
      invoices.amount LIKE '${`%${query}%`}' OR
      invoices.date LIKE '${`%${query}%`}' OR
      invoices.status LIKE '${`%${query}%`}'
  `);
    const totalPages = Math.ceil(Number(count[0].count) / ITEMS_PER_PAGE);
    return totalPages;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch total number of invoices.');
  }
}

export async function fetchInvoiceById(id: string) {
  try {
    const [data] = await (await conn).query<RowDataPacket[]>(`
      SELECT
        invoices.id,
        invoices.customer_id,
        invoices.amount,
        invoices.status
      FROM invoices
      WHERE invoices.id = ${id};
    `);

    const invoice: Invoice = {
      id: data[0].id,
      customer_id: data[0].customer_id,
      amount: data[0].amount / 100,
      status: data[0].status,
      date: ''
    }
    // const invoice = data[0].map((invoice: { amount: number; }) => ({
    //   ...invoice,
    //   // Convert amount from cents to dollars
    //   amount: invoice.amount / 100,
    // }));
    return invoice;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch invoice.');
  }
}

export async function fetchCustomers() {
  try {
    const data = await (await conn).query<RowDataPacket[]>(`
      SELECT
        id,
        name
      FROM customers
      ORDER BY name ASC
    `);

    console.log(data[0]);

    var res: CustomerField[] = []

    for (var item of data[0]) {
      var i: CustomerField = {
        id: item.id,
        name: item.name
      }

      res.push(i)
    }
    return res;
  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to fetch all customers.');
  }
}

export async function fetchFilteredCustomers(query: string) {
  try {
    const data = await sql<CustomersTableType>`
		SELECT
		  customers.id,
		  customers.name,
		  customers.email,
		  customers.image_url,
		  COUNT(invoices.id) AS total_invoices,
		  SUM(CASE WHEN invoices.status = 'pending' THEN invoices.amount ELSE 0 END) AS total_pending,
		  SUM(CASE WHEN invoices.status = 'paid' THEN invoices.amount ELSE 0 END) AS total_paid
		FROM customers
		LEFT JOIN invoices ON customers.id = invoices.customer_id
		WHERE
		  customers.name ILIKE ${`%${query}%`} OR
        customers.email ILIKE ${`%${query}%`}
		GROUP BY customers.id, customers.name, customers.email, customers.image_url
		ORDER BY customers.name ASC
	  `;

    const customers = data.rows.map((customer) => ({
      ...customer,
      total_pending: formatCurrency(customer.total_pending),
      total_paid: formatCurrency(customer.total_paid),
    }));

    return customers;
  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to fetch customer table.');
  }
}

export async function InsertInvoice(invoice: Invoice) {
  try {

    (await conn).execute(`
      INSERT INTO invoices (customer_id, amount, status, date)
    VALUES ('${invoice.customer_id}', ${invoice.amount}, '${invoice.status}', '${invoice.date}')
    `);

  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to insert invoice.');
  }
}

export async function UpdateInvoice(invoice: Invoice) {
  try {

    (await conn).execute(`
    UPDATE invoices
    SET customer_id = '${invoice.customer_id}', amount = ${invoice.amount}, status = '${invoice.status}'
    WHERE id = '${invoice.id}'
  `);

  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to insert invoice.');
  }
}

export async function DeleteInvoiceById(id: string) {
  try {
    (await conn).execute(`DELETE FROM invoices WHERE id = '${id}'`);

  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to insert invoice.');
  }
}