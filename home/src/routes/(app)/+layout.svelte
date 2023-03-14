<script lang="ts">
	import Header from '$lib/components/header.svelte';
	import Sidebar from '$lib/components/sidebar.svelte';
	import type { LayoutServerData } from './$types';
	import '../../app.css';
	import { onMount } from 'svelte';
	import { pwaInfo } from 'virtual:pwa-info';

	export let data: LayoutServerData;

	let darkMode = false;
	let sidebarVisible = false;

	onMount(async () => {
		if (pwaInfo) {
			const { registerSW } = await import('virtual:pwa-register');
			registerSW({
				immediate: true,
				onRegistered(r) {
					console.log(`SW registered: ${r}`);
				},
				onRegisterError(error) {
					console.log(`SW registration error: ${error}`);
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

	<main
		class="flex h-full overflow-y-scroll bg-right-bottom bg-contain bg-no-repeat bg-fixed {data
			.colors[data.page][50]}"
		style:background-image={data.bg_img}
	>
		<Sidebar visible={sidebarVisible} colors={data.colors} />
		<slot />
	</main>
</div>
