<script lang="ts">
	import DeviceCard from './device-card.svelte';
	import type { PageData } from './$types';
	import { MonitorIcon, SunIcon, WindIcon } from 'svelte-feather-icons';

	export let data: PageData;
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="text-lights-400 text-4xl md:text-6xl font-bold">{data.divisionName}</h1>
	<section class="grid grid-cols-4 gap-16 py-12">
		{#each data.divisionDevices as device}
			{#if device.category === 'lights'}
				<DeviceCard name={device.name} id={device.id}><SunIcon class="stroke-accent" /></DeviceCard>
			{:else if device.category === 'media'}
				<DeviceCard name={device.name} id={device.id}
					><MonitorIcon class="stroke-accent" /></DeviceCard
				>
			{:else if device.category === 'climate'}
				<DeviceCard name={device.name} id={device.id}><WindIcon class="stroke-accent" /></DeviceCard
				>
			{/if}
		{/each}
	</section>
</section>
