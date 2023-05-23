<script lang="ts">
	import SidebarEntry from './sidebar-entry.svelte';
	import { page } from '$app/stores';
	import type { ComponentType } from 'svelte';
	import { DropletIcon, MonitorIcon, SunIcon } from 'svelte-feather-icons';
	import type { Room } from '$lib/types/room';
	import type { Category } from '$lib/types/category';

	export let rooms: Room[] = [];
	export let visible = false;
	export let categories: Category[] = [];
	const createRoomURL = '/rooms/create';

	const iconDict: { [key: string]: ComponentType } = {
		Light: SunIcon,
		Media: MonitorIcon,
		Climate: DropletIcon
	};
</script>

<aside
	class="{visible
		? 'flex flex-col absolute sm:static sm:h-full'
		: 'hidden'} sm:w-1/3 z-10 sm:z-0 lg:w-1/5 w-full px-4 bg-white"
>
	{#if rooms.length > 0}
		<h2 class="font-semibold pt-2 text-2xl">Rooms</h2>
	{/if}
	<ul class="w-full">
		{#each rooms as room}
			<SidebarEntry name={room.name} id={room.id} isRoom={true} />
		{/each}
		<a
			href={createRoomURL}
			class="flex w-full p-4 text-black rounded-[15px] cursor-pointer {$page.url.pathname ===
			createRoomURL
				? 'bg-accent text-white'
				: ''}">Add Room</a
		>
	</ul>
	<h2 class="font-semibold pt-6 text-2xl">Categories</h2>
	<ul class="w-full ">
		{#each categories as category}
			<SidebarEntry
				name={category.name}
				id={category.id}
				icon={iconDict[category.name]}
				isRoom={false}
			/>
		{/each}
	</ul>
</aside>
