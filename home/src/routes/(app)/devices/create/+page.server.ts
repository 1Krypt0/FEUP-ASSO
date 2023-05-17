import type { PageServerLoad } from './$types';

export const load = (async ({ url, parent }) => {
	const divisionID = url.searchParams.get('division') || '';
	const type = url.searchParams.get('type') || '';
	const parentData = await parent();
	const rooms = parentData.rooms;
	let name;

	if (type === 'rooms') {
		name = rooms.find((elem) => elem.id == Number.parseInt(divisionID))?.name;
	} else if (type === 'categories') {
		name = divisionID.charAt(0).toUpperCase() + divisionID.slice(1);
	}

	return { name };
}) satisfies PageServerLoad;
