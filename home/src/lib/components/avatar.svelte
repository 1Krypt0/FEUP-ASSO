<script lang="ts">
	import { ChevronDownIcon, MoonIcon, SunIcon } from 'svelte-feather-icons'
	import svelte_logo from '$lib/images/svelte-logo.svg';
	
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

	<ChevronDownIcon />

	<!-- TODO: Change to sm:block when implemented -->
	<button on:click={toggleDarkMode} class="hidden sm:block">
		{#if darkMode}
		 	<SunIcon class="h-6 w-6" />
		{:else}
			<MoonIcon class="h-6 w-6" />
		{/if}
	</button>
</section>
