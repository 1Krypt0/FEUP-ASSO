<script lang="ts">
	import Header from '$lib/components/header.svelte';
	import Sidebar from '$lib/components/sidebar.svelte';
	import '../app.css';
	import type { LayoutServerData } from './$types';

	export let data: LayoutServerData;
	let darkMode = false;
	let sidebarVisible = false;
</script>

<div class:dark={darkMode} class="flex flex-col min-h-screen h-screen">
	<Header bind:darkMode bind:sidebarVisible />

	<main class="flex h-full ">
		<Sidebar visible={sidebarVisible} colors={data.colors} />
		<slot />
	</main>
</div>
