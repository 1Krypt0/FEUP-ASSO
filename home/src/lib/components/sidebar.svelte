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
	class="duration-300 {visible
		? 'w-72'
		: 'w-0 sm:w-24'} flex flex-col items-start justify-between bg-white h-fit sm:min-h-full py-6"
>
	<ul class="w-full px-4">
		{#each entries as entry}
			<SidebarEntry
				route={entry.route}
				name={entry.name}
				icon={entry.icon}
				bg_color={colors[entry.route]}
				{visible}
			/>
		{/each}
	</ul>
	<a class="px-8 hidden sm:block" href="/settings">
		<SettingsIcon />
	</a>
</aside>
