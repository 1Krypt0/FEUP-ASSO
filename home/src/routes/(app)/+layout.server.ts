import type { Category } from '$lib/types/category';
import type { Room } from '$lib/types/room';
import type { LayoutServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

export const load = (async () => {
	const roomRes = await fetch(`${BASE_URL}/rooms/`);
	const rooms: Room[] = await roomRes.json();

	const categoriesRes = await fetch(`${BASE_URL}/categories/`);
	const categories: Category[] = await categoriesRes.json();

	return {
		rooms,
		categories
	};
}) satisfies LayoutServerLoad;
