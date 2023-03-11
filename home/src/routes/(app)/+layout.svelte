<script lang="ts">
	import Header from '$lib/components/header.svelte';
	import Sidebar from '$lib/components/sidebar.svelte';
	import type { LayoutServerData } from './$types';
	import '../../app.css';

	export let data: LayoutServerData;
	let darkMode = false;
	let sidebarVisible = false;
</script>

<div class:dark={darkMode} class="flex flex-col min-h-screen h-screen">
	<Header bind:darkMode bind:sidebarVisible />

	<main class="flex h-full ">
		<Sidebar visible={sidebarVisible} colors={data.colors.sidebarColors} />
		<slot />
	</main>
</div>
