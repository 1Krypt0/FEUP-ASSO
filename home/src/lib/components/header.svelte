<script>
	import Avatar from '$lib/components/avatar.svelte';
	import dropdown from '$lib/assets/align-justify.svg';
	export let darkMode = false;
</script>

<header class="flex justify-between items-center px-8 lg:px-12 py-2">
	<img alt="dropdown menu" class="h-6 w-6" src={dropdown} />
	<Avatar bind:darkMode />
</header>
