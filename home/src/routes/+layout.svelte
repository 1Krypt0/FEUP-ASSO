<script>
	import Header from '$lib/components/header.svelte';
	import Sidebar from '$lib/components/sidebar.svelte';
	import '../app.css';
	
	let darkMode = false;
	let sidebarVisible = false;
</script>

<div class:dark={darkMode} class="flex flex-col min-h-screen h-screen">
	<Header bind:darkMode bind:sidebarVisible />

	<main class="flex h-full">
		<Sidebar visible={sidebarVisible} />
		<slot />
	</main>
</div>
