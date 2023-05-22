<script lang="ts">
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import { error } from '@sveltejs/kit';
	import Avatar from '../../avatar.svelte';
	import { PlusIcon, MinusIcon } from 'svelte-feather-icons';

	let step = 0;

	let CONDITIONS = ['EQ', 'GT', 'LT', 'GTE', 'LTE', 'NEQ'];
	let OPERATIONS = ['AND', 'OR'];
	let STEPS = ['Name your workflow > ', 'Build your workflow > ', 'Define its action'];

	let devices: any[] = [
		{
			id: 123,
			category: 'Light',
			displayName: 'Default name',
			customName: 'My custom name',
			room: 0,
			actions: [
				{
					id: 'bool',
					name: 'Turn On/Off',
					status: '0'
				},
				{
					id: 'range',
					name: 'Brightness',
					properties: {
						min: 0,
						max: 100,
						step: 0
					},
					status: '20'
				}
			]
		}
	];

	let workflow = {
		name: '',
		eventNodes: [
			{
				device: '',
				action: '',
				conditionNodes: [
					{
						condition: '',
						value: '',
						operation: ''
					}
				],
				operation: ''
			}
		],
		actionNode: {
			device: '',
			action: '',
			value: ''
		}
	};

	$: workflow && clearErrors();

	$: errors = {
		name: '',
		events: '',
		action: ''
	};

	function addEventNode() {
		workflow.eventNodes.push({
			device: '',
			action: '',
			conditionNodes: [
				{
					condition: '',
					value: '',
					operation: ''
				}
			],
			operation: ''
		});
		workflow = { ...workflow };
		console.log(workflow);
	}

	function removeEventNode(eventNodeIndex: number) {
		workflow.eventNodes.splice(eventNodeIndex, 1);
		workflow = { ...workflow };
	}

	function addConditionNode(eventNodeIndex: number) {
		workflow.eventNodes[eventNodeIndex].conditionNodes.push({
			condition: '',
			value: '',
			operation: ''
		});
		workflow = { ...workflow };
	}

	function removeConditionNode(eventNodeIndex: number, conditionNodeIndex: number) {
		workflow.eventNodes[eventNodeIndex].conditionNodes.splice(conditionNodeIndex, 1);
		workflow = { ...workflow };
	}

	function checkForMissingFields() {
		let defaultErrorMessage = 'Please fill out all fields';

		if (step === 0) {
			errors.name = workflow.name === '' ? 'Please enter a name for your workflow' : '';
			return errors.name !== '';
		}

		if (step === 2) {
			errors.action =
				workflow.actionNode.device === '' || workflow.actionNode.action === ''
					? defaultErrorMessage
					: '';
			return errors.action !== '';
		}

		let eventNodeIndex = 0;
		let conditionNodeIndex = 0;

		errors.events = '';
		for (const eventNode of workflow.eventNodes) {
			if (
				eventNode.device === '' ||
				eventNode.action === '' ||
				(eventNodeIndex < workflow.eventNodes.length - 1 && eventNode.operation === '')
			)
				errors.events = defaultErrorMessage;

			for (const conditionNode of eventNode.conditionNodes) {
				if (
					conditionNode.condition === '' ||
					conditionNode.value === '' ||
					(conditionNodeIndex < eventNode.conditionNodes.length - 1 &&
						conditionNode.operation === '')
				)
					errors.events = defaultErrorMessage;

				conditionNodeIndex++;
			}

			eventNodeIndex++;
		}

		return errors.events !== '';
	}

	function clearErrors() {
		errors = {
			name: '',
			events: '',
			action: ''
		};
	}

	async function submit() {
		console.log(workflow);

		if (checkForMissingFields()) return;

		// const response = await fetch('/devices/create', {
		// 	method: 'POST',
		// 	body: JSON.stringify(workflow)
		// });

		// if (response.ok) {
		// 	goto('/');
		// }
	}
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="font-bold md:pl-20 pt-12 text-center md:text-start text-5xl">Add a workflow</h1>
	<div class="md:pl-20 mt-4">
		{#each STEPS as stepName, stepIndex}
			<span class={stepIndex <= step ? 'text-primary' : 'text-black'}>{stepName}</span>
		{/each}
	</div>

	<section class="w-full flex items-center justify-center mt-24">
		<form
			method="POST"
			on:submit|preventDefault={submit}
			class="flex flex-col justify-center gap-6"
		>
			{#if step === 0}
				<div class="pl-5">
					<label for="workflow-name">Name</label>
					<input
						bind:value={workflow.name}
						id="workflow-name"
						name="name"
						type="text"
						placeholder="Enter the new workflow name here"
						class="p-2 md:px-5 sm:ml-5 rounded-full"
					/>
				</div>
				{#if errors.name}
					<p class="self-end text-xs text-red-600 font-semibold">{errors.name}</p>
				{/if}

				<button
					type="button"
					class="self-end px-8 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
					on:click={() => {
						if (!checkForMissingFields()) step++;
					}}>Continue</button
				>
			{:else if step === 1}
				<section class="flex flex-col max-h-56 md:max-h-72 lg:max-h-96 overflow-auto px-12">
					{#each workflow.eventNodes as eventNode, eventNodeIndex}
						<section class="flex flex-col mb-4">
							<section class="flex items-center gap-6">
								<!-- event node device -->
								<div class="sm:flex sm:items-center pl-5 pt-5">
									<label for="workflow-event-node-device-{eventNodeIndex}">Device</label>
									<select
										class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
										id="workflow-event-node-device-{eventNodeIndex}"
										name="event-node-device"
										bind:value={eventNode.device}
									>
										{#each devices as device}
											<option value={device.id}>{device.customName}</option>
										{/each}
									</select>
								</div>
								<!-- event node action -->
								{#if eventNode.device != ''}
									<div class="sm:flex sm:items-center pl-5 pt-5">
										<label for="workflow-event-node-action-{eventNodeIndex}">Action</label>
										<select
											class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
											id="workflow-event-node-action-{eventNodeIndex}"
											name="event-node-action"
											bind:value={eventNode.action}
										>
											{#each devices.find((device) => device.id == eventNode.device)['actions'] as action}
												<option value={action.id}>{action.name}</option>
											{/each}
										</select>
									</div>
								{/if}

								{#if workflow.eventNodes.length < 2 || workflow.eventNodes.length - 1 == eventNodeIndex}
									<button
										type="button"
										class="text-center text-white font-bold rounded-full bg-primary mt-5"
										on:click={addEventNode}><PlusIcon /></button
									>
								{:else}
									<button
										type="button"
										class="text-center text-white font-bold rounded-full bg-primary mt-5"
										on:click={() => removeEventNode(eventNodeIndex)}><MinusIcon /></button
									>
								{/if}
							</section>
							<section class="flex flex-col">
								{#each eventNode.conditionNodes as conditionNode, conditionNodeIndex}
									<section class="flex items-center gap-6 ml-24 max-h-56 md:max-h-72 lg:max-h-96">
										<!-- condition node condition -->
										<div class="sm:flex sm:items-center pl-5 pt-5">
											<label
												for="workflow-condition-node-condition-{eventNodeIndex}-{conditionNodeIndex}"
												>Condition</label
											>
											<select
												class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
												id="workflow-condition-node-condition-{eventNodeIndex}-{conditionNodeIndex}"
												name="condition-node-condition"
												bind:value={conditionNode.condition}
											>
												{#each CONDITIONS as condition}
													<option value={condition}>{condition}</option>
												{/each}
											</select>
										</div>
										<!-- condition node value -->
										{#if conditionNode.condition != ''}
											<div class="sm:flex sm:items-center pl-5 pt-5">
												<label
													for="workflow-condition-node-value-{eventNodeIndex}-{conditionNodeIndex}"
													>Value</label
												>
												<input
													bind:value={conditionNode.value}
													id="workflow-condition-node-value-{eventNodeIndex}-{conditionNodeIndex}"
													name="condition-node-value"
													type="text"
													class="p-2 md:px-5 sm:ml-5 rounded-full"
												/>
											</div>
										{/if}

										{#if eventNode.conditionNodes.length < 2 || eventNode.conditionNodes.length - 1 == conditionNodeIndex}
											<button
												type="button"
												class="text-center text-white font-bold rounded-full bg-primary mt-5"
												on:click={() => addConditionNode(eventNodeIndex)}><PlusIcon /></button
											>
										{:else}
											<button
												type="button"
												class="text-center text-white font-bold rounded-full bg-primary mt-5"
												on:click={() => removeConditionNode(eventNodeIndex, conditionNodeIndex)}
												><MinusIcon /></button
											>
										{/if}
									</section>
									<section class="flex items-center gap-6 ml-24 max-h-56 md:max-h-72 lg:max-h-96">
										<!-- condition node operation -->
										{#if eventNode.conditionNodes.length > 1 && conditionNodeIndex < eventNode.conditionNodes.length - 1}
											<div class="sm:flex sm:items-center pl-5 pt-5">
												<label
													for="workflow-condition-node-operation-{eventNodeIndex}-{conditionNodeIndex}"
													>Operation</label
												>
												<select
													class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
													id="workflow-condition-node-operation-{eventNodeIndex}-{conditionNodeIndex}"
													name="condition-node-operation"
													bind:value={conditionNode.operation}
												>
													{#each OPERATIONS as operation}
														<option value={operation}>{operation}</option>
													{/each}
												</select>
											</div>
										{/if}
									</section>
								{/each}
							</section>
							<section class="flex items-center gap-6">
								<!-- event node operation -->
								{#if workflow.eventNodes.length > 1 && eventNodeIndex < workflow.eventNodes.length - 1}
									<div class="sm:flex sm:items-center pl-5 pt-5">
										<label for="workflow-event-node-operation-{eventNodeIndex}">Operation</label>
										<select
											class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
											id="workflow-event-node-operation-{eventNodeIndex}"
											name="event-node-operation"
											bind:value={eventNode.operation}
										>
											{#each OPERATIONS as operation}
												<option value={operation}>{operation}</option>
											{/each}
										</select>
									</div>
								{/if}
							</section>
						</section>
					{/each}
				</section>
				{#if errors.events}
					<p class="self-end text-xs text-red-600 font-semibold">{errors.events}</p>
				{/if}

				<div class="flex items-center self-end">
					<button
						type="button"
						class="px-8 mr-4 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
						on:click={() => step--}>Back</button
					>
					<button
						type="button"
						class="self-end px-8 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
						on:click={() => {
							if (!checkForMissingFields()) step++;
						}}>Continue</button
					>
				</div>
			{:else if step === 2}
				<section class="flex flex-col items-center gap-6 max-h-56 md:max-h-72 lg:max-h-96 py-6">
					<div class="sm:flex sm:items-center pl-5 pt-5">
						<label for="workflow-target-device">Device</label>
						<select
							class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
							id="workflow-target-device"
							name="target-device"
							bind:value={workflow.actionNode.device}
						>
							{#each devices as device}
								<option value={device.id}>{device.customName}</option>
							{/each}
						</select>
					</div>

					{#if workflow.actionNode.device != ''}
						<div class="sm:flex sm:items-center pl-5 pt-5">
							<label for="workflow-target-action">Action</label>
							<select
								class="flex p-2 sm:ml-5 rounded-full text-center bg-white"
								id="workflow-target-action"
								name="target-action"
								bind:value={workflow.actionNode.action}
							>
								{#each devices.find((device) => device.id == workflow.actionNode.device)['actions'] as action}
									<option value={action.id}>{action.name}</option>
								{/each}
							</select>
						</div>
					{/if}

					{#if workflow.actionNode.action != ''}
						<div class="pl-5">
							<label for="workflow-target-value">Value</label>
							<input
								bind:value={workflow.actionNode.value}
								id="workflow-target-value"
								name="target-value"
								type="text"
								class="p-2 md:px-5 sm:ml-5 rounded-full"
							/>
						</div>
					{/if}

					{#if errors.action}
						<p class="self-end text-xs text-red-600 font-semibold">{errors.action}</p>
					{/if}

					<div class="flex items-center self-end">
						<button
							type="button"
							class="px-8 mr-4 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
							on:click={() => step--}>Back</button
						>
						<button
							type="submit"
							class="px-8 text-center text-white font-bold my-6 py-3 rounded-full bg-primary"
							>Create</button
						>
					</div>
				</section>
			{/if}
		</form>
	</section>
</section>
