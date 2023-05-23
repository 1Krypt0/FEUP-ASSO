<script lang="ts">
    import ColorPicker from "svelte-awesome-color-picker";
    export let hex:string;
</script>

<section class="flex-row flex justify-center">
    <ColorPicker bind:hex isAlpha={false} canChangeMode={false}/>
</section>