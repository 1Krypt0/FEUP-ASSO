<script lang="ts">
	import type { PageData } from './$types';
	import WorkflowCard from './workflow-card.svelte';

	export let data: PageData;
	const { workflows } = data;
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="text-lights-400 text-4xl md:text-6xl font-bold">Workflows</h1>

	<section
		class="flex flex-col items-center sm:grid sm:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-16 py-12"
	>
		{#each workflows as workflow}
			<WorkflowCard id={workflow.id} name={workflow.name} />
		{/each}
	</section>
</section>
