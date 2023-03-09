<script>
	import Avatar from '$lib/components/avatar.svelte';
	import { AlignCenterIcon, XIcon } from 'svelte-feather-icons'
	import { fly } from 'svelte/transition';

	export let darkMode = false;
	export let sidebarVisible = false;
	function toggleSidebar() { sidebarVisible = !sidebarVisible; }
</script>

<header class="flex justify-between items-center px-8 py-2 bg-white">
	<button on:click={toggleSidebar}>
		{#if sidebarVisible}
			<XIcon class="h-6 w-6" />
		{:else}
			<AlignCenterIcon class="h-6 w-6" />
		{/if}
	</button>
	<Avatar bind:darkMode />
</header>
