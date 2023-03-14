<script lang="ts">
	import type { PageData } from './$types';
	import type { Device } from '$lib/types/device';
	import { page } from '$app/stores';
	import { PlusIcon } from 'svelte-feather-icons';
	import DeviceCard from '$lib/components/device-card.svelte';

	export let data: PageData;

	let devices: Device[] = data.devices;
</script>

<svelte:head>
	<title>Home by Iota - {data.title}</title>
	<meta name="description" content={`${data.title} Page`} />
</svelte:head>

<section class={devices.length !== 0 ? 'w-full p-6' : 'h-full flex justify-center items-center'}>
	<div class={devices.length !== 0 ? 'mb-4' : ''}>
		<a href={`${$page.url}/add`}>
			<PlusIcon />
		</a>
	</div>
	{#if devices.length !== 0}
		<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-8">
			{#each devices as device}
				<DeviceCard {data} {device} />
			{/each}
		</div>
	{/if}
</section>
