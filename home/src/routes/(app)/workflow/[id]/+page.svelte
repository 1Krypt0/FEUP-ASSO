<script lang="ts">
	import type { PageData } from './$types';
	import { Operation } from '$lib/types/operation';

	export let data: PageData;
	const { workflow } = data;

	function toggleWorkflow() {
		// TODO
	}

	function deleteWorkflow() {
		// TODO
	}
</script>

<section class="w-full px-5 md:px-28">
	<section class="flex justify-between items-center w-full">
		<h1 class="text-4xl md:text-5xl font-bold">{workflow.name}</h1>
		<div>
			<button
				type="button"
				class="px-8 mr-4 self-end text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
				on:click={toggleWorkflow}>{workflow.active ? 'Deactivate' : 'Activate'}</button
			>
			<button
				type="button"
				class="px-8 self-end text-center text-white font-bold my-12 py-3 rounded-full bg-red-500"
				on:click={deleteWorkflow}>Delete</button
			>
		</div>
	</section>

	<section class="flex flex-col items-center py-12">
		<section>
			{#each workflow.eventNodes as eventNode}
				<section class="bg-gray-200 px-12 py-6 w-fit rounded-3xl flex flex-col">
					<section class="my-5">
						<span>When action </span>
						<span class="px-3 py-1 rounded-full bg-yellow-200 font-semibold">{eventNode.action}</span>
						<span> of device </span>
						<span class="px-3 py-1 rounded-full bg-blue-200 font-semibold">{eventNode.device}</span>
						<span> meets conditions:</span>
					</section>

					{#each eventNode.conditionNodes as conditionNode}
						<section class="my-3 ml-12">
							<span>- </span>
							<span class="px-3 py-1 rounded-full bg-green-200">{conditionNode.condition}</span>
							<span class="px-3 py-1 rounded-full bg-purple-200">{conditionNode.value}</span>
							{#if conditionNode.operation !== Operation.NULL}
								<span class="px-3 py-1 rounded-full bg-red-200">{conditionNode.operation}</span>
							{/if}
						</section>
					{/each}
				</section>

				{#if eventNode.operation !== Operation.NULL}
					<section class="flex justify-center w-100 my-12">
						<span class="px-3 py-1 rounded-full bg-red-200">{eventNode.operation}</span>
					</section>
				{/if}
			{/each}
		</section>
		<section class="my-12">
			<span>The action </span>
			<span class="px-3 py-1 rounded-full bg-yellow-200 font-semibold">{workflow.actionNode.action}</span>
			<span> of device </span>
			<span class="px-3 py-1 rounded-full bg-blue-200 font-semibold">{workflow.actionNode.device}</span>
			<span> will be set to value </span>
			<span class="px-3 py-1 rounded-full bg-purple-200">{workflow.actionNode.value}</span>
		</section>
	</section>
</section>
