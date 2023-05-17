<script lang="ts">
	import { page } from '$app/stores';
	import type { ComponentType } from 'svelte';

	export let name = '';
	export let id: number | string = 0;
	export let icon: ComponentType | null = null;
</script>

<div class="flex my-4">
	<a
		href={typeof id === 'number'
			? `/rooms/${id}`
			: typeof id === 'string'
			? `/categories/${id}`
			: ''}
		class="flex w-full p-4 text-black rounded-[15px] cursor-pointer {$page.url.pathname.split(
			'/'
		)[2] === `${id}`
			? 'bg-accent text-white'
			: ''}"
	>
		{#if icon !== null}
			<svelte:component this={icon} />
		{/if}
		<span class={icon !== null ? 'ml-4' : ''}>{name}</span>
	</a>
</div>
