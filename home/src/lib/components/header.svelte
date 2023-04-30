<script>
	import Avatar from '$lib/components/avatar.svelte';
	import { SettingsIcon, XIcon, SunIcon, MoonIcon, AlignJustifyIcon } from 'svelte-feather-icons';

	export let darkMode = false;
	export let sidebarVisible = false;

	function toggleSidebar() {
		sidebarVisible = !sidebarVisible;
	}

	function toggleDarkMode() {
		darkMode = !darkMode;
	}
</script>

<header class="flex justify-between items-center bg-white z-10 px-8 py-2">
	<div class="flex items-center gap-6">
		<button on:click={toggleSidebar}>
			{#if sidebarVisible}
				<XIcon class="h-6 w-6" />
			{:else}
				<AlignJustifyIcon class="h-6 w-6" />
			{/if}
		</button>
		{#if sidebarVisible}
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
