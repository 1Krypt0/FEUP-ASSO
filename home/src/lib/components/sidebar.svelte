<script lang="ts">
	import { SettingsIcon, SunIcon, MonitorIcon, DropletIcon } from 'svelte-feather-icons'

	export let visible = false;
</script>

<!-- TODO: Dynamically set selected option -->
<!-- TODO: Make mobile responsive -->
<aside
	style:width={visible ? '18rem' : '6rem'}
	class="duration-300 flex flex-col items-start justify-between min-h-full bg-white py-6"
>
	<ul class="w-full px-4">
		<li class="flex items-center mb-8">
			<!-- TODO: Dynamically set flex and w-full if option is selected -->
			<button
				class="bg-yellow-300 text-white rounded-[15px] p-4 flex w-full"
			 	on:click={() => {}}>
				<SunIcon />
				<!-- TODO: If sidebar is visible and option is selected, it goes here -->
				{#if visible}
					<span class="ml-8">Lighting</span>
				{/if}
			</button>
			<!-- TODO: If sidebar is visible and option is not selected, it goes here -->
		</li>
		<li class="flex items-center mb-8">
			<button 
				class="bg-sky-300 text-white rounded-[15px] p-4" 
				on:click={() => {}}>
				<MonitorIcon />
			</button>
			{#if visible}
				<span class="ml-4">Media</span>
			{/if}
		</li>
		<li class="flex items-center mb-8">
			<button 
				class="bg-green-300 text-white rounded-[15px] p-4" 
				on:click={() => {}}>
				<DropletIcon />
			</button>
			{#if visible}
				<span class="ml-4">Climate</span>
			{/if}
		</li>
	</ul>
	<button class="px-8" on:click={() => {}}>
		<SettingsIcon />
	</button>
</aside>
