<script lang="ts">
	import type { PageData } from './$types';
	import { PlusIcon } from 'svelte-feather-icons';
	import WorkflowCard from './workflow-card.svelte';

	export let data: PageData;
	const { workflows } = data;
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<section class="w-full flex justify-between items-center pt-12">
		<h1 class="text-4xl md:text-6xl font-bold">Workflows</h1>
		<a href="/workflow/create">
			<PlusIcon />
		</a>
	</section>

	<section
		class="flex flex-col items-center sm:grid sm:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-16 py-12"
	>
		{#each workflows as workflow}
			<WorkflowCard id={workflow.id} name={workflow.name} />
		{/each}
	</section>
</section>
