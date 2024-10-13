'use server';
import { z } from 'zod'
import { Invoice } from './definitions';
import { InsertInvoice } from './data';

import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';

const FormSchema = z.object({
    id: z.string(),
    customerId: z.string(),
    amount: z.coerce.number(),
    status: z.enum(['pending', 'paid']),
    date: z.string(),
});

const CreateInvoice = FormSchema.omit({ id: true, date: true });

export async function createInvoice(formData: FormData) {
    const { customerId, amount, status } = CreateInvoice.parse({
        customerId: formData.get('customerId'),
        amount: formData.get('amount'),
        status: formData.get('status'),
    });

    const amountInCents = amount * 100;
    const date = new Date().toISOString().split('T')[0];
    // Test it out:
    console.log(customerId, amountInCents, status, date);

    var invoice: Invoice = {
        id: "",
        customer_id: customerId,
        amount: amountInCents,
        status: status,
        date: date
    }

    InsertInvoice(invoice);

    revalidatePath('/dashboard/invoices');
    redirect('/dashboard/invoices');
}