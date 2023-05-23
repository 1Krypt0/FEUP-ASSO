import { PUBLIC_URL } from '$env/static/public';
import type { Workflow } from '$lib/types/workflow';

export async function createWorkflow(workflow: Workflow) {
	const data = await fetch(`${PUBLIC_URL}/workflows`, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(workflow)
	})
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.error('Error creating workflow', error);
			return { error };
		});

	return data;
}

export async function getWorkflows(): Promise<Workflow[]> {
	const data = await fetch(`${PUBLIC_URL}/workflows`)
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.error('Error fetching workflows', error);
			return [];
		});

	return data;
}

export async function getWorkflow(id: string): Promise<Workflow> {
	const data = await fetch(`${PUBLIC_URL}/workflows/${id}`)
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.error(`Error fetching workflow with ID ${id}`, error);
			return {};
		});

	return data;
}
