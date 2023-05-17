<script lang="ts">
	import { SunIcon, MonitorIcon, DropletIcon } from 'svelte-feather-icons';
	import SidebarEntry from './sidebar-entry.svelte';
	import { page } from '$app/stores';

	export let rooms: { name: string; id: number }[] = [];
	export let visible = false;
	const createRoomURL = '/rooms/create';

	const categories = [
		{
			name: 'Lights',
			id: 'lights',
			icon: SunIcon
		},
		{
			name: 'Media',
			id: 'media',
			icon: MonitorIcon
		},
		{
			name: 'Climate',
			id: 'climate',
			icon: DropletIcon
		}
	];
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
			<SidebarEntry name={category.name} id={category.id} icon={category.icon} />
		{/each}
	</ul>
</aside>
