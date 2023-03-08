<script lang="ts">
	import svelte_logo from '$lib/images/svelte-logo.svg';
	import moon from '$lib/assets/moon.svg';
	import sun from '$lib/assets/sun.svg';
	import Dropdown from './dropdown.svelte';
	export let darkMode = false;

	function toggleDarkMode() {
		darkMode = !darkMode;
	}
</script>

<section class="flex items-center gap-4">
	<!-- TODO: Complete with dynamic username -->
	<p class="text-lg font-extrabold hidden sm:block">Sample User</p>

	<!-- TODO: Complete with dynamic profile pic -->
	<img class="w-12 h-12" alt="user profile" src={svelte_logo} />

	<Dropdown />

	<!-- TODO: Change to sm:block when implemented -->
	<button on:click={toggleDarkMode} class="hidden sm:hidden">
		<img alt="Dark/Light Mode toggle" src={darkMode ? sun : moon} />
	</button>
</section>
