<script lang="ts">
	import type { Device } from '$lib/types/device';
	import { page } from '$app/stores';
	import { PlusIcon } from 'svelte-feather-icons';
	import DeviceCard from '$lib/components/device-card.svelte';

	export let data;

	const device = data.page.charAt(0).toUpperCase() + data.page.slice(1);

	let devices: Device[] = data.devices || [];
</script>

<svelte:head>
	<title>Home by Iota - {device}</title>
	<meta name="description" content={`${device} Page`} />
</svelte:head>

<section
	class={devices.length === 0
		? 'h-full flex justify-center items-center'
		: 'grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-8 m-8 sm:ml-32'}
>
	{#if devices.length === 0}
		<a href={`${$page.url}/add`}>
			<PlusIcon />
		</a>
	{:else}
		{#each devices as device}
			<DeviceCard {device} {data} />
		{/each}
	{/if}
</section>
