import type { Condition } from './condition';
import type { Operation } from './operation';

type ConditionNode = {
	condition: Condition;
	value: string;
	operation: Operation;
};

type EventNode = {
	device: string;
	action: string;
	conditionNodes: ConditionNode[];
	operation: Operation;
};

type ActionNode = {
	device: string;
	action: string;
	value: string;
};

export type Workflow = {
	id: string;
	name: string;
	active: boolean;
	eventNodes: EventNode[];
	actionNode: ActionNode;
};
