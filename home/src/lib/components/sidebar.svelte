<script lang="ts">
	import { SettingsIcon, SunIcon, MonitorIcon, DropletIcon } from 'svelte-feather-icons';
	import SidebarEntry from './sidebar-entry.svelte';

	export let visible = false;
	let entries = [
		{
			route: 'lights',
			name: 'Lighting',
			icon: SunIcon,
			bg: 'bg-yellow-300'
		},
		{
			route: 'media',
			name: 'Media',
			icon: MonitorIcon,
			bg: 'bg-blue-300'
		},
		{
			route: 'climate',
			name: 'Climate',
			icon: DropletIcon,
			bg: 'bg-green-300'
		}
	];
</script>

<aside
	class="duration-300 {visible
		? 'w-72'
		: 'w-0 sm:w-24'} flex flex-col items-start justify-between min-h-full bg-white py-6"
>
	<ul class="w-full px-4">
		{#each entries as route}
			<SidebarEntry
				route={route.route}
				name={route.name}
				icon={route.icon}
				bg_color={route.bg}
				{visible}
			/>
		{/each}
	</ul>
	<a class="px-8 hidden sm:block" href="/settings">
		<SettingsIcon />
	</a>
</aside>
