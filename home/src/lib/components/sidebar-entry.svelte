<script lang="ts">
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import { SunIcon } from 'svelte-feather-icons';

	export let route = '';
	export let name = '';
	export let visible = false;
	export let icon = SunIcon;
	export let bg_color = '';

	function handleSelect() {
		goto(`/${route}`);
	}
</script>

<div class="flex items-center mb-8">
	<input
		type="radio"
		id={route}
		name="route"
		value={route}
		class="hidden peer/{route}"
		checked={$page.url.pathname === `/${route}`}
	/>
	<label
		on:click={handleSelect}
		for={route}
		class="inline-flex items-center justify-center w-full p-4 text-black rounded-[15px] cursor-pointer peer-checked/{route}:{bg_color} peer-checked/{route}:text-white"
	>
		<svelte:component this={icon} />
		{#if visible}
			<span class="ml-8">{name}</span>
		{/if}
	</label>
</div>
