<script lang="ts">
	import WorkflowCard from './workflow-card.svelte';

	// TODO: fetch workflows
  let workflows : any[] = []
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="text-lights-400 text-4xl md:text-6xl font-bold">Workflows</h1>

	<section
		class="flex flex-col items-center sm:grid sm:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-16 py-12"
	>
		{#each workflows as workflow}
			<WorkflowCard name={workflow.name} id={workflow.id} />
		{/each}
	</section>
</section>
