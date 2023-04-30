<script lang="ts">
	import { SunIcon, MonitorIcon, DropletIcon } from 'svelte-feather-icons';
	import SidebarEntry from './sidebar-entry.svelte';

	export let visible = false;
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

<aside class="{visible ? 'flex' : 'hidden'} bg-white">
	<ul class="w-full px-4">
		{#each entries as entry}
			<SidebarEntry route={entry.route} name={entry.name} icon={entry.icon} {visible} />
		{/each}
	</ul>
</aside>
