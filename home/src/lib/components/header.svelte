<script>
	import Avatar from '$lib/components/avatar.svelte';
	import dropdown from '$lib/assets/align-justify.svg';
	import close from '$lib/assets/x.svg';
	import { fly } from 'svelte/transition';

	export let darkMode = false;
	export let sideBarVisible = true;
	function toggleSideBar() {
		sideBarVisible = !sideBarVisible;
	}
</script>

<header class="flex justify-between items-center px-8  py-2 bg-white">
	<button on:click={toggleSideBar}>
		{#if sideBarVisible}
			<img in:fly alt="dropdown menu" class="h-6 w-6" src={dropdown} />
		{:else}
			<img in:fly alt="dropdown menu" class="h-6 w-6" src={close} />
		{/if}
	</button>
	<Avatar bind:darkMode />
</header>
