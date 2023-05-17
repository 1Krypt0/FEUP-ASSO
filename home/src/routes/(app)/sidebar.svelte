<script lang="ts">
	import SidebarEntry from './sidebar-entry.svelte';
	import { page } from '$app/stores';
	import type { ComponentType } from 'svelte';
	import { DropletIcon, MonitorIcon, SunIcon } from 'svelte-feather-icons';

	export let rooms: { name: string; id: number }[] = [];
	export let visible = false;
	export let categories: { name: string; id: number }[] = [];
	const createRoomURL = '/rooms/create';

	const iconDict: { [key: string]: ComponentType } = {
		lights: SunIcon,
		media: MonitorIcon,
		climate: DropletIcon
	};
</script>

<aside class="{visible ? 'flex flex-col' : 'hidden'} w-1/5 px-4 bg-white">
	{#if rooms.length > 0}
		<h2 class="font-semibold pt-2 text-2xl">Rooms</h2>
	{/if}
	<ul class="w-full">
		{#each rooms as room}
			<SidebarEntry name={room.name} id={room.id} />
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
			<SidebarEntry name={category.name} id={category.id} icon={iconDict[category.id]} />
		{/each}
	</ul>
</aside>
