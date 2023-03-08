<script>
	import Header from '$lib/components/header.svelte';
	import './styles.css';
	import '../app.css';
	let darkMode = false;
</script>

<div class:dark={darkMode} class="flex flex-col min-h-screen">
	<Header bind:darkMode />

	<main class="flex flex-1 flex-col p-4 w-full max-w-5xl my-0 mx-auto box-border">
		<slot />
	</main>
</div>
