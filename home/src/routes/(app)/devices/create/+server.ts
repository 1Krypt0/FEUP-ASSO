import type { RequestHandler } from './$types';

const BASE_URL = 'http://localhost:8080';

const headers = new Headers();
headers.append('Content-Type', 'application/json');

export const POST = (async ({ request }) => {
	const data = await request.json();

	const customName = data['customName'];
	const roomID = data['roomID'];
	const deviceID = data['deviceID'];
	const displayName = data['displayName'];

	// TODO: Check received fields and update backend

	console.log(
		`Name: ${customName}, Room: ${roomID}, Category: ${deviceID}, Device Name: ${displayName}`
	);

	// TODO: Save new device to database, put to /devices/id
	const res = await fetch(`${BASE_URL}/devices/${deviceID}`, {
		method: 'PUT',
		body: JSON.stringify({
			name: displayName,
			room: roomID
		}),
		headers: headers
	});

	const resData = await res.json();
	const message = resData.message;

	if (res.ok) {
		return new Response(null, { status: 200 });
	} else {
		return new Response(JSON.stringify({ message }), { status: 405 });
	}
}) satisfies RequestHandler;
