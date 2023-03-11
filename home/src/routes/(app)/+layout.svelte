<script lang="ts">
	import { onMount } from 'svelte';
	import { page } from '$app/stores';
	import Header from '$lib/components/header.svelte';
	import Sidebar from '$lib/components/sidebar.svelte';
	import '../../app.css';

	let darkMode = false;
	let sidebarVisible = false;

	let bg_image = '';
	let bg_color = '';
	onMount(() => {
		let route = $page.url.pathname.slice(1);
		bg_image = `bg-[url('$lib/assets/bg-${route}.svg')]`;
		bg_color = `bg-${route}-50`;
	});
</script>

<div class:dark={darkMode} class="flex flex-col min-h-screen h-screen">
	<Header bind:darkMode bind:sidebarVisible />

	<main class="flex h-full {bg_image} {bg_color} bg-right-bottom bg-contain bg-no-repeat">
		<Sidebar visible={sidebarVisible} />
		<slot />
	</main>
</div>
