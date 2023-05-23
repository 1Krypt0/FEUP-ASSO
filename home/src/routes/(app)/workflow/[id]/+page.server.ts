import type { PageServerLoad } from './$types';
import type { Device } from '$lib/types/device';
import type { Workflow } from '$lib/types/workflow';
import { Condition } from '$lib/types/condition';
import { Operation } from '$lib/types/operation';

export const load = (async ({ params, parent }) => {
	const parentData = await parent();

	// TODO: actually fetch workflow based on id and devices

	let workflow: Workflow = {
		id: 1,
		active: true,
		name: 'My first workflow',
		eventNodes: [
			{
				device: 1,
				action: 1,
				conditionNodes: [
					{
						condition: Condition.EQ,
						value: '1',
						operation: Operation.NULL
					}
				],
				operation: Operation.AND
			},
			{
				device: 1,
				action: 2,
				conditionNodes: [
					{
						condition: Condition.GT,
						value: '10',
						operation: Operation.AND
					},
					{
						condition: Condition.LT,
						value: '30',
						operation: Operation.NULL
					}
				],
				operation: Operation.NULL
			}
		],
		actionNode: {
			device: 1,
			action: 1,
			value: '0'
		}
	};

	let devices: Device[] = [
		{
			id: 1,
			category: 'light',
			displayName: 'Light',
			customName: 'My Light',
			room: 1,
			actions: [
				{
					id: 1,
					action: {
						id: 1,
						type: 'bool',
						required: undefined
					},
					name: 'Turn on/off',
					properties: undefined,
					state: '0'
				},
				{
					id: 2,
					action: {
						id: 2,
						type: 'range',
						required: ['min', 'max', 'step']
					},
					name: 'Brightness',
					properties: { min: 0, max: 100, step: 1 },
					state: '0'
				}
			]
		}
	];

	// map workflow devices and actions to corresponding names
	for (let eventNode of workflow.eventNodes) {
		const device = devices.find((device) => device.id === eventNode.device);
		eventNode.device = device ? device.customName : '';

		if (device === undefined) continue;
		const action = device.actions.find((action) => action.id === eventNode.action);
		eventNode.action = action ? action.name : '';
	}

	const actionDevice = devices.find((device) => device.id === workflow.actionNode.device);
	workflow.actionNode.device = actionDevice ? actionDevice.customName : '';

	if (actionDevice !== undefined) {
		const actionAction = actionDevice.actions.find(
			(action) => action.id === workflow.actionNode.action
		);
		workflow.actionNode.action = actionAction ? actionAction.name : '';
	}

	return { workflow, devices };
}) satisfies PageServerLoad;
