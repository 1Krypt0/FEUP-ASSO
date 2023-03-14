import { PUBLIC_URL } from '$env/static/public';
import type { Device } from '$lib/types/device';

export async function createDevice(device: Device) {
	const data = await fetch(`${PUBLIC_URL}/devices/new`, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(device)
	})
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.log('Error creating device', error);
			return { error };
		});

	return data;
}

export async function getDevices(type: string): Promise<Device[]> {
	const data = await fetch(`${PUBLIC_URL}/devices?type=${type}`)
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.log(`Error fetching devices of type ${type}`, error);
			return [];
		});

	return data;
}

export async function getDevice(id: string): Promise<Device> {
	const data = await fetch(`${PUBLIC_URL}/devices/${id}`)
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.log(`Error fetching device with ID ${id}`, error);
			return {};
		});

	return data;
}
