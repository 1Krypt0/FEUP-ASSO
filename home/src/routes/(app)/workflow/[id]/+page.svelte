<script lang="ts">
	import type { PageData } from './$types';
	import { Operation } from '$lib/types/operation';

	export let data: PageData;
	const { workflow } = data;
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="font-bold md:pl-20 pt-12 text-center md:text-start text-5xl">{workflow.name}</h1>
	<section class="w-full md:pl-20 mt-24">
		<section class="">
			{#each workflow.eventNodes as eventNode}
				<section class="my-5">
					<span>When action </span>
					<span class="px-2 bg-yellow-200">{eventNode.action}</span>
					<span> of device </span>
					<span class="px-2 bg-blue-200">{eventNode.device}</span>
					<span> mets conditions:</span>
				</section>

				{#each eventNode.conditionNodes as conditionNode}
					<section class="my-5 ml-12">
						<span>- </span>
						<span class="px-2 bg-green-200">{conditionNode.condition}</span>
						<span class="px-2 bg-purple-200">{conditionNode.value}</span>
						{#if conditionNode.operation !== Operation.NULL}
							<span class="px-2 bg-red-200">{conditionNode.operation}</span>
						{/if}
					</section>
				{/each}

				{#if eventNode.operation !== Operation.NULL}
					<section class="my-12">
						<span class="px-2 bg-red-200">{eventNode.operation}</span>
					</section>
				{/if}
			{/each}
		</section>
		<section class="my-12">
			<span>The action </span>
			<span class="px-2 bg-yellow-200">{workflow.actionNode.action}</span>
			<span> of device </span>
			<span class="px-2 bg-blue-200">{workflow.actionNode.device}</span>
			<span> will be set to value </span>
			<span class="px-2 bg-purple-200">{workflow.actionNode.value}</span>
		</section>
	</section>
</section>
