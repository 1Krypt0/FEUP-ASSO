<script lang="ts">
	import Header from './header.svelte';
	import Sidebar from './sidebar.svelte';
	import '../../app.css';
	import { onMount } from 'svelte';
	import { pwaInfo } from 'virtual:pwa-info';
	import type { LayoutServerData } from './$types';

	export let data: LayoutServerData;

	let darkMode = false;
	let sidebarVisible = false;

	onMount(async () => {
		if (pwaInfo) {
			const { registerSW } = await import('virtual:pwa-register');
			registerSW({
				immediate: true,
				onRegisterError(error) {
					console.error(`SW registration error: ${error}`);
				}
			});
		}
	});

	$: webManifest = pwaInfo ? pwaInfo.webManifest.linkTag : '';
</script>

<svelte:head>
	{@html webManifest}
</svelte:head>

<div class:dark={darkMode} class="flex flex-col min-h-screen h-screen">
	<Header bind:darkMode bind:sidebarVisible />

	<main class="flex flex-col sm:flex-row h-full w-full bg-light">
		<Sidebar visible={sidebarVisible} rooms={data.rooms} categories={data.categories} />
		<slot />
	</main>
</div>
