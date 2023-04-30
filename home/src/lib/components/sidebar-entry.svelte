<script lang="ts">
	import { page } from '$app/stores';
	import { SunIcon } from 'svelte-feather-icons';

	export let route = '';
	export let name = '';
	export let icon = SunIcon;
</script>

<div class="flex my-4">
	<a
		data-sveltekit-reload
		href="/{route}"
		class="flex w-full p-4 text-black rounded-[15px] cursor-pointer {$page.url.pathname.split(
			'/'
		)[1] === `${route}`
			? 'bg-accent text-white'
			: ''}"
	>
		<svelte:component this={icon} />
		<span class="ml-8">{name}</span>
	</a>
</div>
