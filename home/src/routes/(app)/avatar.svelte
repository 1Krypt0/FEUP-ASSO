<script lang="ts">
	import { MoonIcon, SunIcon } from 'svelte-feather-icons';
	import svelte_logo from '$lib/images/svelte-logo.svg';
	import Dropdown from './dropdown.svelte';

	export let darkMode = false;

	function toggleDarkMode() {
		darkMode = !darkMode;
	}
</script>

<section class="flex items-center gap-4">
	<p class="text-lg font-semibold hidden sm:block">Bessie Cooper</p>

	<img class="w-12 h-12" alt="user profile" src={svelte_logo} />

	<Dropdown />

	<button on:click={toggleDarkMode} class="hidden sm:block">
		{#if darkMode}
			<SunIcon class="h-6 w-6" />
		{:else}
			<MoonIcon class="h-6 w-6" />
		{/if}
	</button>
</section>
