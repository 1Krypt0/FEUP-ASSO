<script lang="ts">
	import { page } from '$app/stores';
	import { goto } from '$app/navigation';
	import { PlusIcon } from 'svelte-feather-icons';
	import DeviceCard from '$lib/components/device-card.svelte';

	export let data;

	const device = $page.params.page.charAt(0).toUpperCase() + $page.params.page.slice(1);

	let devices: { id: string; name: string }[] = [];

	function handleAddDevice() {
		goto(`${$page.url}/add`);
	}
</script>

<svelte:head>
	<title>Home by Iota - {device}</title>
	<meta name="description" content={`${device} Page`} />
</svelte:head>

<section
	class={devices.length === 0
		? 'h-full flex justify-center'
		: 'grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-8 m-8 sm:ml-32'}
>
	{#if devices.length === 0}
		<button on:click={handleAddDevice}>
			<PlusIcon />
		</button>
	{:else}
		{#each devices as device}
			<DeviceCard {device} {data} />
		{/each}
	{/if}
</section>
