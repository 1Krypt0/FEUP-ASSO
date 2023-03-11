<script lang="ts">
	import { page } from '$app/stores';
	import { goto } from '$app/navigation';
	import { PlusIcon } from 'svelte-feather-icons';

	const device = $page.params.page.charAt(0).toUpperCase() + $page.params.page.slice(1);

	function handleAddDevice() {
		goto(`${$page.url}/add`);
	}
</script>

<svelte:head>
	<title>Home by Iota - {device}</title>
	<meta name="description" content={`${device} Page`} />
</svelte:head>

<section class="w-full flex justify-center">
	<button on:click={handleAddDevice}>
		<PlusIcon />
	</button>
</section>
