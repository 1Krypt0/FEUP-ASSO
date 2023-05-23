<script lang="ts">
	import { fly } from 'svelte/transition';
	import { ChevronDownIcon } from 'svelte-feather-icons';
	let visible = false;
	function toggleVisibility() {
		visible = !visible;
	}
</script>

<div class="relative inline-block text-left">
	<button class="pt-2" on:click={toggleVisibility}>
		<ChevronDownIcon />
	</button>

	{#if visible}
		<div
			class="absolute right-0 z-10 mt-5 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
			role="menu"
			aria-orientation="vertical"
			aria-labelledby="menu-button"
			tabindex="-1"
			transition:fly
		>
			<div class="py-1" role="none">
				<!-- Active: "bg-gray-100 text-gray-900", Not Active: "text-gray-700" -->
				<a
					href="/workflow"
					class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100"
					role="menuitem"
					tabindex="-1"
					id="menu-item-0">My Workflows</a
				>
				<a
					href="/settings"
					class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100"
					role="menuitem"
					tabindex="-1"
					id="menu-item-1">Account Settings</a
				>
				<a
					href="/about"
					class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100"
					role="menuitem"
					tabindex="-1"
					id="menu-item-2">About Us</a
				>
				<a
					href="/logout"
					class="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-100"
					role="menuitem"
					tabindex="-1"
					id="menu-item-3">Logout</a
				>
			</div>
		</div>
	{/if}
</div>
