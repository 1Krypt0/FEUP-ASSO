<script lang="ts">
	import { page } from '$app/stores';
	import type { ComponentType } from 'svelte';

	export let name = '';
	export let isRoom = true;
	export let id: number = 0;
	export let icon: ComponentType | null = null;

	$: route = $page.url.pathname.split('/');

	$: highlight =
		(isRoom && route.at(1) === 'rooms' && Number.parseInt(route.at(2) || '0') === id) ||
		(!isRoom && route.at(1) === 'categories' && Number.parseInt(route.at(2) || '0') === id);
</script>

<div class="flex my-4">
	<a
		href={isRoom ? `/rooms/${id}` : `/categories/${id}`}
		class="flex w-full p-4 text-black rounded-[15px] cursor-pointer {highlight
			? 'bg-accent text-white'
			: ''}"
	>
		{#if icon !== null}
			<svelte:component this={icon} />
		{/if}
		<span class={icon !== null ? 'ml-4' : ''}>{name}</span>
	</a>
</div>
