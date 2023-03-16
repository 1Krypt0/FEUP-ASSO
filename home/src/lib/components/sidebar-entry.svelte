<script lang="ts">
	import { page } from '$app/stores';
	import { SunIcon } from 'svelte-feather-icons';

	export let visible = false;
	export let route = '';
	export let name = '';
	export let icon = SunIcon;
	export let bg_color = '';
</script>

<div class="flex my-4">
	<a
		data-sveltekit-reload
		href="/{route}"
		class="flex {visible
			? 'justify-start'
			: 'justify-center'} w-full p-4 text-black rounded-[15px] cursor-pointer 
			   {$page.url.pathname.split('/')[1] === `${route}` ? bg_color + ' text-white' : ''}"
	>
		<svelte:component this={icon} />
		{#if visible}
			<span class="ml-8">{name}</span>
		{/if}
	</a>
</div>
