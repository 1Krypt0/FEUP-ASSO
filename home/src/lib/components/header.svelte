<script>
	import Avatar from '$lib/components/avatar.svelte';
	import { AlignCenterIcon, SettingsIcon, XIcon, SunIcon, MoonIcon } from 'svelte-feather-icons';
	import { fly } from 'svelte/transition';

	export let darkMode = false;
	export let sidebarVisible = false;

	function toggleSidebar() {
		sidebarVisible = !sidebarVisible;
	}

	function toggleDarkMode() {
		darkMode = !darkMode;
	}
</script>

<header class="flex justify-between items-center px-8 py-2 bg-white">
	<div class="flex gap-6 items-center">
		<button on:click={toggleSidebar}>
			{#if sidebarVisible}
				<XIcon class="h-6 w-6" />
			{:else}
				<AlignCenterIcon class="h-6 w-6" />
			{/if}
		</button>
		{#if sidebarVisible}
			<a class="sm:hidden" href="/settings">
				<SettingsIcon />
			</a>

			<button on:click={toggleDarkMode} class=" sm:hidden">
				{#if darkMode}
					<SunIcon class="h-6 w-6" />
				{:else}
					<MoonIcon class="h-6 w-6" />
				{/if}
			</button>
		{/if}
	</div>
	<Avatar bind:darkMode />
</header>
