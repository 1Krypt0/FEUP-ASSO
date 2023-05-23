<script lang="ts">
	import { DropletIcon, PercentIcon, PowerIcon, XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';

	export let name: string;
	export let roomID: number;
    
    let setIntensity = true;
    function intensityToBeSet() {
        setIntensity = true;
    }
    function colourToBeSet() {
        setIntensity = false;
    }
</script>

<section class="flex-col flex drop-shadow-lg bg-white text-xl px-6 py-6 h-3/6 w-2/6 rounded-3xl">
    <div class="flex-row flex justify-between">
        <div class="flex justify-normal text-2xl font-bold gap-3 items-center">
            <slot />
            {name}
        </div>
        <div>
            <a href="/room/{roomID}"><XIcon /></a>
        </div>
    </div>
    <div>
        <button><PowerIcon/></button>
        <button on:click={intensityToBeSet}><PercentIcon/></button>
        <button on:click={colourToBeSet}><DropletIcon/></button>
    </div>
    <div class="h-full flex-col flex justify-center">
        {#if setIntensity}
            <RangeAction value={60} min={0} max={100} step={1} device_type="light"></RangeAction>
        {:else}
            <ColorPickerAction hex="#00ff00"></ColorPickerAction>
        {/if}
    </div>
</section>