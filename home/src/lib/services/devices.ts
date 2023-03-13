import { PUBLIC_URL } from '$env/static/public';
import type { Device } from '$lib/types/device';

export async function createDevice(device: Device) {
	await fetch(`${PUBLIC_URL}/devices/new`, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(device)
	});
}

export async function getDevices(type: string): Promise<Device[]> {
	const data = await fetch(`${PUBLIC_URL}/devices?type=${type}`)
		.then((response) => response.json())
		.then((data) => {
			return data;
		})
		.catch((error) => {
			console.log(error);
			return [];
		});

	return data;
}
