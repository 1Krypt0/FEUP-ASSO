<script lang="ts">
	import { XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';

	export let name: string;
	export let roomID: number;
</script>

<section class="flex-col flex drop-shadow-lg bg-white text-xl px-6 py-6 h-3/6 w-2/6 rounded-3xl">
    <div class="flex-row flex justify-between">
        <div class="flex justify-normal text-2xl font-bold gap-3 items-center">
            <slot />
            {name}
        </div>
        <div class="">
            <a href="/room/{roomID}"><XIcon /></a>
        </div>
    </div>
    <div class="h-full flex-col flex justify-center">
        <!-- <RangeAction value={60} min={0} max={100} step={1} device_type="light"></RangeAction> -->
        <ColorPickerAction hex="#00ff00"></ColorPickerAction>
    </div>
</section>