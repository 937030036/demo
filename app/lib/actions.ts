'use server';
import { z } from 'zod'
import { Invoice } from './definitions';
import { DeleteInvoiceById, InsertInvoice, UpdateInvoice } from './data';

import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';

import { signIn } from '@/auth';
import { AuthError } from 'next-auth';


export async function authenticate(
    prevState: string | undefined,
    formData: FormData,
) {
    try {
        await signIn('credentials', formData);
    } catch (error) {
        if (error instanceof AuthError) {
            console.log(error.message)
            switch (error.type) {
                case 'CredentialsSignin':
                    return 'Invalid credentials.';
                case 'CallbackRouteError':
                    return 'username or password wrong';
                default:
                    return 'Something went wrong.';
            }
        }
        throw error;
    }
}


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

    const invoice: Invoice = {
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

const UpdateInvoiceSchema = FormSchema.omit({ id: true, date: true });

export async function updateInvoice(id: string, formData: FormData) {
    const { customerId, amount, status } = UpdateInvoiceSchema.parse({
        customerId: formData.get('customerId'),
        amount: formData.get('amount'),
        status: formData.get('status'),
    });

    const amountInCents = amount * 100;
    const invoice: Invoice = {
        id: id,
        customer_id: customerId,
        amount: amountInCents,
        status: status,
        date: ''
    }

    UpdateInvoice(invoice);

    revalidatePath('/dashboard/invoices');
    redirect('/dashboard/invoices');
}

export async function deleteInvoice(id: string) {
    DeleteInvoiceById(id);
    revalidatePath('/dashboard/invoices');
}