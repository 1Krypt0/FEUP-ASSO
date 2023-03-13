<script lang="ts">
	import { SettingsIcon, SunIcon, MonitorIcon, DropletIcon } from 'svelte-feather-icons';
	import SidebarEntry from './sidebar-entry.svelte';

	export let visible = false;
	export let colors: any;

	let entries = [
		{
			route: 'lights',
			name: 'Lighting',
			icon: SunIcon
		},
		{
			route: 'media',
			name: 'Media',
			icon: MonitorIcon
		},
		{
			route: 'climate',
			name: 'Climate',
			icon: DropletIcon
		}
	];
</script>

<aside
	class="{visible
		? 'w-52'
		: 'hidden sm:w-24'} fixed top-0 sm:h-full sm:flex flex-col items-start justify-between bg-white"
>
	<ul class="w-full mt-16 px-4">
		{#each entries as entry}
			<SidebarEntry
				route={entry.route}
				name={entry.name}
				icon={entry.icon}
				bg_color={colors[entry.route][400]}
				{visible}
			/>
		{/each}
	</ul>
	<a class="mb-8 px-8 hidden sm:block" href="/settings">
		<SettingsIcon />
	</a>
</aside>
