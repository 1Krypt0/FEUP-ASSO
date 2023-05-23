import type { Condition } from './condition';
import type { Operation } from './operation';

type ConditionNode = {
	condition: Condition;
	value: string;
	operation: Operation;
};

type EventNode = {
	device: number | string;
	action: number | string;
	conditionNodes: ConditionNode[];
	operation: Operation;
};

type ActionNode = {
	device: number | string;
	action: number | string;
	value: string;
};

export type Workflow = {
	id: number;
	name: string;
	active: boolean;
	eventNodes: EventNode[];
	actionNode: ActionNode;
};
